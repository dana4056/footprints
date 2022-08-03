package footprints.footprints.service.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.roomInfo.RoomInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomInfoServiceImpl {
    private final RoomInfoRepository roomInfoRepository;

//    @Override
    public void join(RoomInfoDTO roomInfoDTO) {
        roomInfoRepository.save_d(roomInfoDTO);
    }
}
