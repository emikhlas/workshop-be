package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveCreateDto;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupAchieveServ {
    GroupAchieveDto createGroupAchieve(GroupAchieveCreateDto groupAchieveDto);
    GroupAchieveDto updateGroupAchieve(UUID id, GroupAchieveCreateDto groupAchieveDto);
    Optional<GroupAchieveDto> getGroupAchieveById(UUID id);
    List<GroupAchieveDto> getAllGroupAchieve();
    boolean deleteGroupAchieve(UUID id);
}
