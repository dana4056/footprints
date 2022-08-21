package footprints.footprints.repository.roominfo;

import footprints.footprints.domain.roomInfo.RoomInfo;
import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Slf4j
@RequiredArgsConstructor
public class RoomInfoRepositoryImpl implements RoomInfoRepository{


    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(RoomInfoDTO roomInfoDTO){
        RoomInfo roomInfo = new RoomInfo(roomInfoDTO.getMember(), roomInfoDTO.getPost());
        em.persist(roomInfo);
    }
}
