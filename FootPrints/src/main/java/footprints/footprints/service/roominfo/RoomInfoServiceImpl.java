package footprints.footprints.service.roominfo;

import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.roominfo.RoomInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;

    @Override
    public void join(RoomInfoDTO roomInfoDTO){
        roomInfoRepository.save(roomInfoDTO);
    }

    @Override
    public void remove(RoomInfoDTO roomInfoDTODTO){
        roomInfoRepository.delete(roomInfoDTODTO);
    }

}
