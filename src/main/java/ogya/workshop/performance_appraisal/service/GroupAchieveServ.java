package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupAchieveServ {
    GroupAchieveDto createGroupAchieve(GroupAchieveDto groupAchieveDto);
    GroupAchieveDto updateGroupAchieve(UUID id, GroupAchieveDto groupAchieveDto);
    Optional<GroupAchieveDto> getGroupAchieveById(UUID id);
    List<GroupAchieveDto> getAllGroupAchieve();
    boolean deleteGroupAchieve(UUID id);
}
