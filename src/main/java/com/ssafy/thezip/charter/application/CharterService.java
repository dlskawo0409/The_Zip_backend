package com.ssafy.thezip.charter.application;

import com.ssafy.thezip.charter.domain.Charter;
import com.ssafy.thezip.charter.domain.CharterRepository;
import com.ssafy.thezip.charter.dto.request.CharterInfoBySidoGugunDongDTO;
import com.ssafy.thezip.charter.dto.request.CharterInfoDTO;
import com.ssafy.thezip.charter.dto.request.CharterInsertDTO;
import com.ssafy.thezip.charter.dto.request.CharterUpdateDTO;
import com.ssafy.thezip.charter.dto.response.CharterInfoListResponseDTO;
import com.ssafy.thezip.common.Image.application.ImageService;
import com.ssafy.thezip.common.Image.domain.Image;
import com.ssafy.thezip.common.Image.domain.ImageType;
import com.ssafy.thezip.common.Image.dto.request.ImageInsertByURLDTO;
import com.ssafy.thezip.common.Image.dto.response.ImageResponseDTO;
import com.ssafy.thezip.dongcode.domain.DongCodeRepository;
import com.ssafy.thezip.member.domain.MemberRepository;
import com.ssafy.thezip.member.domain.Role;
import com.ssafy.thezip.member.dto.request.CustomMemberDetails;
import com.ssafy.thezip.member.exception.MemberErrorCode;
import com.ssafy.thezip.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CharterService {

    private  final  CharterRepository charterRepository;
    private final MemberRepository memberRepository;
    private final DongCodeRepository dongCodeRepository;
    private final ImageService imageService;


    @Value("${basic.image.charter:defaultValue}")
    private String charterBasicImageName;

    public List<Charter> getCharterList(CharterInfoDTO charterInfoDTO,
                                        CustomMemberDetails loginMember){
        return charterRepository.findCharterByDongCode(charterInfoDTO, loginMember == null ? null : loginMember.getMemberId());
    }

    public Long getCharterResultCount(CharterInfoDTO charterInfoDTO){
        return charterRepository.findCountByDongCode(charterInfoDTO);
    }

    public List<CharterInfoListResponseDTO> getCharterListByName(String name){
        return charterRepository.findByName(name);
    }
    public List<Charter> getCharterById(Long charterId, CustomMemberDetails loginMember){
        return charterRepository.findById(charterId, loginMember == null ? null : loginMember.getMemberId());
    }

    public List<Charter> getCharterByLikes(String charterKind , Integer count){
        return charterRepository.findByLikes(charterKind, count);
    }



    @Transactional
    public List<Charter> getCharterListBySidoGugunDong(CharterInfoBySidoGugunDongDTO charterInfoBySidoGugunDongDTO,
                                                       CustomMemberDetails loginMember){
        String dongCode = dongCodeRepository.findDongCodeByDong(charterInfoBySidoGugunDongDTO.getSido(),
                charterInfoBySidoGugunDongDTO.getGugun(),
                charterInfoBySidoGugunDongDTO.getDong());

        charterInfoBySidoGugunDongDTO.changeDongCode(dongCode);

        return charterRepository.findCharterBySidoGugunDong(charterInfoBySidoGugunDongDTO,
                loginMember == null ? null : loginMember.getMemberId());
    }


    @Transactional
    public void postCharter(CharterInsertDTO charterInsertDTO, MultipartFile multipartFile, CustomMemberDetails loginMember) throws IOException {
        Long memberId  = memberRepository.findByUsername(loginMember.getUsername()).getMemberId();
        charterInsertDTO.changeDongCode(); // 동 코드를 분리

        Charter charter = Charter.builder()
                .preCode(charterInsertDTO.getPreCode())
                .postCode(charterInsertDTO.getPostCode())
                .charterKind(charterInsertDTO.getCharterKind())
                .floor(charterInsertDTO.getFloor())
                .dealYear(charterInsertDTO.getDealYear())
                .dealMonth(charterInsertDTO.getDealMonth())
                .dealDay(charterInsertDTO.getDealDay())
                .deposit(charterInsertDTO.getDeposit())
                .rent(charterInsertDTO.getRent())
                .name(charterInsertDTO.getName())
                .size(charterInsertDTO.getSize())
                .constructionYear(charterInsertDTO.getConstructionYear())
                .buildingUse(charterInsertDTO.getBuildingUse())
                .charterGu(charterInsertDTO.getCharterGu())
                .charterDong(charterInsertDTO.getCharterDong())
                .bonbun(charterInsertDTO.getBonbun())
                .bubun(charterInsertDTO.getBubun())
                .memberId(memberId)
                .build();

        charterRepository.save(charter);

        if(multipartFile == null){
            ImageInsertByURLDTO imageInsertByURLDTO = new ImageInsertByURLDTO();
            imageInsertByURLDTO.setImageType(ImageType.CHARTER);
            imageInsertByURLDTO.setImageUrl(charterBasicImageName);
            imageInsertByURLDTO.setReferenceId(String.valueOf(charter.getCharterId()));
            imageService.upload(imageInsertByURLDTO);
            return;
        }

        imageService.upload(multipartFile, String.valueOf(charter.getMemberId()), ImageType.CHARTER);
    }

    public void updateCharter(CharterUpdateDTO charterUpdateDTO, CustomMemberDetails loginMember){
        charterUpdateDTO.changeDongCode();
        Long memberId = loginMember.getAuthorities().iterator().next().getAuthority().equals(Role.ADMIN.getKey()) ? null : loginMember.getMemberId();

        charterRepository.updateCharter(charterUpdateDTO, memberId);
    }

    public List<Charter> getCharterList(CustomMemberDetails loginMember){
        return charterRepository.findCharterByMemberId(loginMember.getMemberId());
    }

    @Transactional
    public void deleteCharter(Long charterId , CustomMemberDetails loginMember){
        Long memberId = loginMember.getAuthorities().iterator().next().getAuthority().equals(Role.ADMIN.getKey()) ? null : loginMember.getMemberId();

        List<ImageResponseDTO> imageResponseDTOList = imageService.getImage(String.valueOf(charterId), ImageType.CHARTER);
        for(ImageResponseDTO  imageResponseDTO : imageResponseDTOList){
            imageService.delete(imageResponseDTO.getImageId());
        }
        charterRepository.delete(charterId, memberId);
    }



}
