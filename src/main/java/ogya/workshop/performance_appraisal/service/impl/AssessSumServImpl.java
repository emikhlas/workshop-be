package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;

import ogya.workshop.performance_appraisal.dto.assesssum.*;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDetailDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumReqDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;
import ogya.workshop.performance_appraisal.dto.assesssum.GroupedResultDto;

import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveInfoWithCountDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoWithCountDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoWithDivDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.AssessSumRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.repository.specification.AssessSumSpec;
import ogya.workshop.performance_appraisal.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AssessSumServImpl implements AssessSumServ {
    private final Logger Log = LoggerFactory.getLogger(AssessSumServImpl.class);
    @Autowired
    private AssessSumRepo assessSumRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmpAchieveSkillServ empAchieveSkillServ;

    @Autowired
    private EmpAttitudeSkillServ empAttitudeSkillServ;

    @Autowired
    private GroupAchieveServ groupAchieveSkillServ;

    @Autowired
    private GroupAttitudeSkillServ groupAttitudeSkillServ;


    @Override
    public Page<AssessSumDto> getFilteredAssessSum(String searchTerm, Integer year, List<UUID> divisionIds, Pageable pageable) {
        Log.info("Start getFilteredAssessSum in AssessSumServImpl");

        Specification<AssessSum> specification = Specification.where(null);

        if (searchTerm != null && !searchTerm.isEmpty()) {
            Specification<AssessSum> searchSpec = Specification
                    .where(AssessSumSpec.hasName(searchTerm))
                    .or(AssessSumSpec.hasEmail(searchTerm))
                    .or(AssessSumSpec.hasPosition(searchTerm));
            specification = specification.and(searchSpec);
        }

        if (year != null) {
            specification = specification.and(AssessSumSpec.hasYear(year));
        }

        if (divisionIds != null && !divisionIds.isEmpty()) {
            specification = specification.and(AssessSumSpec.hasDivision(divisionIds));
        }

        Page<AssessSum> response = assessSumRepo.findAll(specification, pageable);
        Page<AssessSumDto> assessSumDtoPage = response.map(AssessSumDto::fromEntity);

        Log.info("End getFilteredAssessSum in AssessSumServImpl");
        return assessSumDtoPage;
    }

    @Override
    public List<AssessSumDto> getAllAssessSum() {

        Log.info("Start getAllAssessSum in AssessSumServImpl");
        List<AssessSum> response = assessSumRepo.findAll();
        List<AssessSumDto> assessSumList = new ArrayList<>();
        for (AssessSum assessSum : response) {
            assessSumList.add(AssessSumDto.fromEntity(assessSum));
        }
        Log.info("End getAllAssessSum in AssessSumServImpl");

        return assessSumList;
    }
    @Override
    public List<AssessSumDto> getAllAssessSumByYear(Integer year) {
        Log.info("Start getAssessSumByYear in AssessSumServImpl");
        List<AssessSum> assessSums = assessSumRepo.findByYear(year);
        List<AssessSumDto> assessSumList = new ArrayList<>();
        for(AssessSum assessSum : assessSums) {
            assessSumList.add(AssessSumDto.fromEntity(assessSum));
        }
        Log.info("End getAssessSumByYear in AssessSumServImpl");
        return assessSumList;
    }
  
    @Override
    public List<AssessSumWithUserDto> getAllAssessSumWithUserByYear(Integer year) {
        Log.info("Start getAssessSumByYear in AssessSumServImpl");
        List<AssessSum> assessSums = assessSumRepo.findByYear(year);
        List<AssessSumWithUserDto> assessSumList = new ArrayList<>();
        for(AssessSum assessSum : assessSums) {
            assessSumList.add(AssessSumWithUserDto.fromEntity(assessSum));
        }
        Log.info("End getAssessSumByYear in AssessSumServImpl");
        return assessSumList;
    }

    @Override
    public List<Integer> getDistinctAssessmentYears() {
        Log.info("Start getDistinctAssessmentYears in AssessSumServImpl");
        List<Integer> years = assessSumRepo.findDistinctAssessmentYears();
        Log.info("End getDistinctAssessmentYears in AssessSumServImpl");
        return years;
    }

    @Override
    public List<AssessSumDto> getAssessSumByUserId(UUID userId) {
        Log.info("Start getAssessSumByUserId in AssessSumServImpl");
        List<AssessSum> response = assessSumRepo.findByUserId(userId);
        List<AssessSumDto> assessSumList = new ArrayList<>();
        for (AssessSum assessSum : response) {
            assessSumList.add(AssessSumDto.fromEntity(assessSum));
        }
        Log.info("End getAssessSumByUserId in AssessSumServImpl");
        return assessSumList;
    }

    @Override
    public AssessSumDto getAssessSumById(UUID id) {
        Log.info("Start getAssessSumById in AssessSumServImpl");
        AssessSum assessSum = assessSumRepo.findById(id).orElseThrow(() -> new RuntimeException("AssessSum not found"));
        Log.info("End getAssessSumById in AssessSumServImpl");
        return AssessSumDto.fromEntity(assessSum);
    }

    @Override
    public AssessSumDto createAssessSum(AssessSumReqDto assessSumReqDto) {
        Log.info("Start createAssessSum in AssessSumServImpl");
        AssessSum currentAssessSum = assessSumRepo.findByUserIdAndYear(assessSumReqDto.getUserId(), assessSumReqDto.getYear());
        if (currentAssessSum != null) {
            return updateAssessSum(currentAssessSum.getId(), assessSumReqDto);
        } else {
            User user = userRepo.findById(assessSumReqDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User  not found"));
            AssessSum assessSum = AssessSumReqDto.toEntity(assessSumReqDto);
            assessSum.setUser(user);
            assessSum.setCreatedAt(LocalDateTime.now());
            Log.info("End createAssessSum in AssessSumServImpl");
            return AssessSumDto.fromEntity(assessSumRepo.save(assessSum));
        }
    }

    @Override
    public AssessSumDto updateAssessSum(UUID id, AssessSumReqDto assessSumReqDto) {
        Log.info("Start updateAssessSum in AssessSumServImpl");

        AssessSum assessSum = assessSumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessSum not found"));

        if (assessSumReqDto.getUserId() != null) {
            User user = userRepo.findById(assessSumReqDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User  not found"));
            assessSum.setUser(user);
        }

        if (assessSumReqDto.getYear() != null) {
            assessSum.setYear(assessSumReqDto.getYear());
        }

        if (assessSumReqDto.getScore() != null) {
            assessSum.setScore(assessSumReqDto.getScore());
        }

        if (assessSumReqDto.getStatus() != null) {
            assessSum.setStatus(assessSumReqDto.getStatus());
        }
        assessSum.setUpdatedAt(LocalDateTime.now());

        Log.info("End updateAssessSum in AssessSumServImpl");
        return AssessSumDto.fromEntity(assessSumRepo.save(assessSum));
    }
    @Override
    public Boolean deleteAssessSum(UUID id) {
        Log.info("Start deleteAssessSum in AssessSumServImpl");
        if (assessSumRepo.existsById(id)) {
            assessSumRepo.deleteById(id);
            Log.info("End deleteAssessSum in AssessSumServImpl");
            return true;
        }
        throw new RuntimeException("AssessSum not found");
    }

    @Override
    public AssessSumDto generateAssessSum(UUID userId, Integer year) {
        Map<String, Object> generatedData = fetchAssessSumData(userId, year);
        int score = (Integer) generatedData.get("score");
        AssessSumReqDto requestData = new AssessSumReqDto();
        requestData.setUserId(userId);
        requestData.setYear(year);
        requestData.setScore(score);
        requestData.setStatus(0);

        return createAssessSum(requestData);
    }

    @Override
    public void generateAssessSumsForAllUsers(Integer year) {
        List<UUID> allUserIds = userRepo.findAllId();

        List<AssessSumDto> assessSums = new ArrayList<>();

        for (UUID userId : allUserIds) {
            AssessSumDto summary = generateAssessSum(userId, year);
            assessSums.add(summary);
        }

    }

    @Override
    public AssessSumDetailDto getAssessSumDetail(UUID userId, Integer year) {
        Log.info("Start getAssessSumDetail in AssessSumServImpl");
        Map<String, Object> generatedData = fetchAssessSumData(userId, year);

        int score = (Integer) generatedData.get("score");
        List<GroupedResultDto<EmpAchieveSkillDto>> groupedAchieveResults =
                (List<GroupedResultDto<EmpAchieveSkillDto>>) generatedData.get("achieveResults");
        List<GroupedResultDto<EmpAttitudeSkillDto>> groupedAttitudeResults =
                (List<GroupedResultDto<EmpAttitudeSkillDto>>) generatedData.get("attitudeResults");

        AssessSumDto assessSum = new AssessSumDto();
        UserInfoWithDivDto user = new UserInfoWithDivDto();
        user.setId(userId);
        assessSum.setUser(user);
        assessSum.setYear(year);
        assessSum.setScore(score);
        assessSum.setStatus(0);
        AssessSumDetailDto assessSumDetail = new AssessSumDetailDto();

        assessSumDetail.setAssessSum(assessSum);
        assessSumDetail.setGroupedAchieveResults(groupedAchieveResults);
        assessSumDetail.setGroupedAttitudeResults(groupedAttitudeResults);

        return assessSumDetail;
    }

    private Map<String, Object> fetchAssessSumData(UUID userId, Integer year) {
        Log.info("Start generateAssessSum in AssessSumServImpl");

        List<EmpAttitudeSkillDto> empAttitudeSkills = empAttitudeSkillServ.getEmpAttSkillByUserId(userId, year, true);
        List<EmpAchieveSkillDto> empAchievementSkills = empAchieveSkillServ.getAllEmpUserAchieveByUserId(userId, year, true);
        List<GroupAttitudeSkillInfoWithCountDto> groupAttitudeSkills = groupAttitudeSkillServ.getGroupAttitudeSkillWithCount().stream().filter(g -> g.getEnabled() == 1).toList();
        List<GroupAchieveInfoWithCountDto> groupAchievements = groupAchieveSkillServ.getGroupAchieveInfoWithCount().stream().filter(g -> g.getEnabled() == 1 ).toList();

        List<GroupedResultDto<EmpAchieveSkillDto>> groupedAchieveResults = groupDataByCategory(
                empAchievementSkills,
                this::getAchievementGroupName,
                this::getAchievementPercentage,
                "Achievements",
                groupAchievements
        );

        List<GroupedResultDto<EmpAttitudeSkillDto>> groupedAttitudeResults = groupDataByCategory(
                empAttitudeSkills,
                this::getAttitudeGroupName,
                this::getAttitudePercentage,
                "Attitude and Skills",
                groupAttitudeSkills
        );

        List<GroupedResultDto> combinedResults = new ArrayList<>();
        combinedResults.addAll(groupedAchieveResults);
        combinedResults.addAll(groupedAttitudeResults);

        Map<String, Object> summary = calculateSummary(combinedResults);
        List<GroupedResultDto<EmpAchieveSkillDto>> adjustedAchieveResults = combinedResults.stream()
                .filter(result -> "Achievements".equals(result.getSection()))
                .map(result -> (GroupedResultDto<EmpAchieveSkillDto>) result)
                .collect(Collectors.toList());

        List<GroupedResultDto<EmpAttitudeSkillDto>> adjustedAttitudeResults = combinedResults.stream()
                .filter(result -> "Attitude and Skills".equals(result.getSection()))
                .map(result -> (GroupedResultDto<EmpAttitudeSkillDto>) result)
                .collect(Collectors.toList());

        summary.put("achieveResults", adjustedAchieveResults);
        summary.put("attitudeResults", adjustedAttitudeResults);
        return summary;
    }

    private <T> List<GroupedResultDto<T>> groupDataByCategory(
            List<T> data,
            Function<T, String> groupNameFn,
            Function<T, Number> percentageFn,
            String section,
            List<?> groupData) {

        Map<String, GroupedData<T>> groupedMap = data.stream()
                .collect(Collectors.groupingBy(
                        groupNameFn,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                items -> {
                                    int totalScore = items.stream()
                                            .mapToInt(this::getItemScore)
                                            .sum();
                                    double percentage = items.isEmpty()
                                            ? 0.0
                                            : percentageFn.apply(items.get(0)).doubleValue();
                                    return new GroupedData<>(totalScore, percentage, items);
                                }
                        )
                ));


        for (Object groupObj : groupData) {
            String groupName = getGroupNameFromObject(groupObj);
            double percentage = getPercentageFromObject(groupObj);

            groupedMap.putIfAbsent(
                    groupName,
                    new GroupedData<>(0, percentage, Collections.emptyList())
            );
        }

        return groupedMap.entrySet().stream()
                .map(entry -> {
                    String groupName = entry.getKey();
                    GroupedData<T> groupedData = entry.getValue();
                    int count = groupData == null ? 1 : getGroupCount(groupName, section, groupData);

                    return new GroupedResultDto<>(
                            section,
                            groupName,
                            groupedData.totalScore / count,
                            groupedData.percentage,
                            groupedData.items
                    );
                })
                .collect(Collectors.toList());
    }

    private String getGroupNameFromObject(Object obj) {
        if (obj instanceof GroupAchieveInfoWithCountDto) {
            return ((GroupAchieveInfoWithCountDto) obj).getGroupAchievementName();
        }
        if (obj instanceof GroupAttitudeSkillInfoWithCountDto) {
            return ((GroupAttitudeSkillInfoWithCountDto) obj).getGroupAttitudeName();
        }
        throw new IllegalArgumentException("Unsupported group data type");
    }

    private double getPercentageFromObject(Object obj) {
        if (obj instanceof GroupAchieveInfoWithCountDto) {
            return ((GroupAchieveInfoWithCountDto) obj).getPercentage();
        }
        if (obj instanceof GroupAttitudeSkillInfoWithCountDto) {
            return ((GroupAttitudeSkillInfoWithCountDto) obj).getPercentage();
        }
        throw new IllegalArgumentException("Unsupported group data type");
    }

    private int getGroupCount(String groupName, String section, List<?> groupData) {
        if ("Attitude and Skills".equals(section)) {
            return groupData.stream()
                    .filter(g -> ((GroupAttitudeSkillInfoWithCountDto) g).getGroupAttitudeName().equals(groupName))
                    .mapToInt(g -> ((GroupAttitudeSkillInfoWithCountDto) g).getAttitudeCount()) // Call getAttitudeCount() on the instance 'g'
                    .findFirst()
                    .orElse(1); // Return 1 if no match is found

        } else {
            return groupData.stream().filter(g -> ((GroupAchieveInfoWithCountDto) g).getGroupAchievementName().equals(groupName))
                    .mapToInt(g -> ((GroupAchieveInfoWithCountDto) g).getAchievementCount())
                    .findFirst()
                    .orElse(1);
        }
    }

    private Map<String, Object> calculateSummary(List<GroupedResultDto> combinedData) {
        double totalWeight = combinedData.stream().mapToDouble(GroupedResultDto::getPercentage).sum();
        double totalScore = 0;

        List<Integer> roundedPercentages = new ArrayList<>();
        List<Double> recalculatedPercentages = new ArrayList<>();

        for (GroupedResultDto item : combinedData) {
            double recalculatedPercentage = (item.getPercentage() / totalWeight) * 100;
            int roundedPercentage = (int) Math.round(recalculatedPercentage);

            recalculatedPercentages.add(recalculatedPercentage);
            roundedPercentages.add(roundedPercentage);

            System.out.println("Score: " + item.getTotalScore() + ", Percentage: " + roundedPercentage);
        }

        int remainingPercentage = 100 - roundedPercentages.stream().mapToInt(Integer::intValue).sum();
        distributeRemainingPercentage(roundedPercentages, remainingPercentage);

        for (int i = 0; i < combinedData.size(); i++) {
            GroupedResultDto item = combinedData.get(i);
            int adjustedPercentage = roundedPercentages.get(i);

            item.setPercentage(adjustedPercentage);
            totalScore += Math.round(item.getTotalScore() * (adjustedPercentage / 100.0));
        }

        int assScore = (int) Math.round(totalScore);
        System.out.println("assScore: " + assScore);

        Map<String, Object> result = new HashMap<>();
        result.put("data", combinedData);
        result.put("score", assScore);

        return result;
    }

    private void distributeRemainingPercentage(List<Integer> percentages, int remaining) {
        if (percentages == null || percentages.isEmpty()) {
            return;
        }
        int direction = remaining > 0 ? 1 : -1;
        int i = 0;

        while (remaining != 0) {
            if (percentages.get(i) > 0) {
                percentages.set(i, percentages.get(i) + direction);
                remaining -= direction;
            }
            i = (i + 1) % percentages.size();
        }
    }

    private String getAchievementGroupName(Object item) {
        return ((EmpAchieveSkillDto) item).getAchievement().getGroupAchievement().getGroupAchievementName();
    }

    private double getAchievementPercentage(Object item) {
        return ((EmpAchieveSkillDto) item).getAchievement().getGroupAchievement().getPercentage();
    }

    private String getAttitudeGroupName(Object item) {
        return ((EmpAttitudeSkillDto) item).getAttitudeSkill().getGroupAttitudeSkill().getGroupName();
    }

    private double getAttitudePercentage(Object item) {
        return ((EmpAttitudeSkillDto) item).getAttitudeSkill().getGroupAttitudeSkill().getPercentage();
    }

    private int getItemScore(Object item) {
        if (item instanceof EmpAchieveSkillDto) {
            return ((EmpAchieveSkillDto) item).getScore();
        } else if (item instanceof EmpAttitudeSkillDto) {
            return ((EmpAttitudeSkillDto) item).getScore();
        }
        return 0;
    }

    class GroupedData<T> {
        int totalScore = 0;
        double percentage = 0.0;
        List<T> items = new ArrayList<>();

        public GroupedData() {
        }

        public GroupedData(int totalScore, double percentage, List<T> items) {
            this.totalScore = totalScore;
            this.percentage = percentage;
            this.items = items;
        }
    }

    @Override
    public AssessSumWithUserDto getAssessmentSummary(UUID userId, Integer year) {
        Log.info("Start getAssessmentSummary in AssessSumServImpl");

        AssessSum assessSum = assessSumRepo.findByUserIdAndYear(userId, year);
        if (assessSum == null) {
            throw new RuntimeException("Assessment summary not found for userId: " + userId + " and year: " + year);
        }

        Log.info("End getAssessmentSummary in AssessSumServImpl");
        return AssessSumWithUserDto.fromEntity(assessSum);
    }

//    @Override
//    public AssessSumWithUserDto updateAssessSumStatusToActive(UUID id) {
//        Log.info("Start updateAssessSumStatusToActive in AssessSumServImpl");
//
//        // Ambil entitas AssessSum berdasarkan ID
//        AssessSum assessSum = assessSumRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("AssessSum not found"));
//
//        // Perbarui status menjadi 1
//        assessSum.setStatus(1);
//        assessSum.setUpdatedAt(LocalDateTime.now());
//
//        // Simpan perubahan ke database
//        AssessSum updatedAssessSum = assessSumRepo.save(assessSum);
//
//        Log.info("End updateAssessSumStatusToActive in AssessSumServImpl");
//
//        // Kembalikan entitas yang diperbarui sebagai DTO
//        return AssessSumWithUserDto.fromEntity(updatedAssessSum);
//    }

    @Override
    public AssessSumWithUserDto updateAssessSumStatusToApprove(UUID id) {
        Log.info("Start updateAssessSumStatusToApprove in AssessSumServImpl");

        AssessSum assessSum = assessSumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessSum not found"));

        assessSum.setStatus(1);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        assessSum.setApproverId(creator);

        assessSum.setApprovalDate(LocalDateTime.now());

        AssessSum updatedAssessSum = assessSumRepo.save(assessSum);

        Log.info("End updateAssessSumStatusToApprove in AssessSumServImpl");

        return AssessSumWithUserDto.fromEntity(updatedAssessSum);
    }

    @Override
    public AssessSumWithUserDto updateAssessSumStatusToUnapprove(UUID id) {
        Log.info("Start updateAssessSumStatusToUnapprove in AssessSumServImpl");

        AssessSum assessSum = assessSumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessSum not found"));

        assessSum.setStatus(0);

        AssessSum updatedAssessSum = assessSumRepo.save(assessSum);

        Log.info("End updateAssessSumStatusToUnapprove in AssessSumServImpl");

        return AssessSumWithUserDto.fromEntity(updatedAssessSum);
    }

}
