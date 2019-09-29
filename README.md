# scorpio
- Implementaion of Eureka Service Discovery,Feign Client, Hystrix Fallback,Ribbon Load Balancer:

- Eureka:
1. http://localhost:8761/ 

- Weather Report : 

1. http://localhost:8090/weather-report/findOne 
2. http://localhost:8090/weather-report/findAll

- Weather Service : 
1. http://localhost:8091/weather-service/findAll 
2. http://localhost:8091/weather-service/findOne


---------------------




===================================================

=========================================================

   public Map<Integer, SkillInfo> buildSkillInfoMap(List<SkillCategory> skillCategoryList) {
        Map<Integer, SkillInfo> skillInfoMap = skillCategoryList.stream().collect(
                HashMap::new,
                (hashmap, skillCategory) -> skillCategory.getSkills().forEach(
                        skill -> hashmap.put(skill.getSkillId(), new SkillInfo.Builder()
                                .setSkillName(skill.getName())
                                .setSkillCategoryName(skillCategory.getName())
                                .setComponentList(skill.getSkillComponents())
                                .build())
                ),
                Map::putAll
        );
        return skillInfoMap;
    };a
===================================================

 final Map<Integer, List<SkillComponentHistoryEntity>> skillComponentHistoryEntitiesMap =
                skillComponentHistoryEntities.stream().collect(Collectors.groupingBy(SkillComponentHistoryEntity::getSkillComponentId));
        final Map<Integer, List<SkillComponentHistory>> skillComponentHistoryMap = skillComponentHistoryEntitiesMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entity -> {
                    try {
                        return objectMapper.readValue(objectMapper.writeValueAsString(entity.getValue()),
                                new TypeReference<List<SkillComponentHistory>>() {
                                });
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
        ));
=======================================================================================================
       final List<UserInfo> userInfoList = uniqueEmployeeList.stream().map(employeeDao -> new UserInfo.Builder()
                .setUserName(employeeDao.getUsername())
                .setFullName(employeeDao.getFullName())
                .setEmail(employeeDao.getEmail())
                .setUserReference(employeeDao.getStaffReference())
                .build()
        ).collect(Collectors.toList());

=====================================================================================================================

  skillComponentHistoryMap
                .forEach((key,value)-> value.forEach(skillComponentHistory -> {
                    skillComponentHistory.setSkillComponent(skillComponentMap.get(key));
                }));
==============================================================================

  public void getValidUserSkill(List<UserSkill> userSkillList) {
        userSkillList.stream()
                .filter(userSkill -> userSkill.getComplete())
                .map(userSkill -> {
                    final List<UserSkillComponent> userSkillComponents = userSkill.getUserSkillComponents()
                            .stream()
                            .filter(userSkillComponent -> Objects.nonNull(userSkillComponent.getExpiryDate()) &&
                                    userSkillComponent.getExpiryDate().after(new Date()))
                            .collect(toList());
                    userSkill.setUserSkillComponents(userSkillComponents);
                    return userSkill;
                }).collect(toList());
    }

==================================================

serSkill -> {
            final List<UserSkillComponent> userSkillComponents = userSkill.getUserSkillComponents();
            userSkillComponents.forEach(userSkillComponent -> {
                if(Objects.nonNull(userSkillComponent.getExpiryDate()) && userSkillComponent.getExpiryDate().before(new Date())){
                    userSkillList.remove(userSkill);
                }
            });
======================================================

   public List<UserSkill> removeDuplicatedFromUserList(List<UserSkill> userSkillList){
        List<UserSkill> uniqueUserSkillList = userSkillList.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(UserSkill::getUserReference))),
                        ArrayList::new));
        return uniqueUserSkillList;
    }
--------------------------------------------------------------------------
 scheduleAttendeeMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, value -> {
                    List<ScheduleAttendee> uniqueSchedulePerUser = value.getValue().stream().collect(collectingAndThen(toCollection(() ->
                                    new TreeSet<>(Comparator.comparingInt(ScheduleAttendee::getScheduleId))),
                            ArrayList::new));
                    return uniqueSchedulePerUser;
                }));
//alwa ys return new list else get unrelated compiler errors
--------------------------------------------------------------------------------------

    private void addEmptyListForUserWithNoSchedules(UserDepartmentTeam[] userDepartmentTeams, Map<String, List<ScheduleAttendee>> scheduleAttendeeMap) {

        final List<String> userReferenceList = Arrays.stream(userDepartmentTeams)
		.map(UserDepartmentTeam::getStaffReference)
		.collect(Collectors.toList());


        userReferenceList.stream().forEach(key->{
                 scheduleAttendeeMap.computeIfAbsent(key,value->Collections.emptyList());
                }
        );
    }
=========================================================================================================
// to remove item from list
 final List<EmployeeDao> employeeDaos = positionDaos.stream().map(PositionDao::getOwner).collect(Collectors.toList());
employeeDaos.removeIf(employeeDao -> (employeeDao.getEmployeeId() == managerDao.getEmployeeId()));
==================================================================================================================

        final List<List<UserDepartmentTeamDao>> userTeamDaoList = Arrays.stream(teamDepartments).collect(ArrayList::new,
                (list, teamDepartment) -> {
                    final List<UserDepartmentTeamDao> departmentTeamList = userDepartmentTeamRepository.findByDepartmentTeamId(teamDepartment.getTeamId(),

teamDepartment.getDepartmentId());
                    list.add(departmentTeamList);
                }, ArrayList::addAll);


        final Set<String> staffReferenceSet = userTeamDaoList.stream().flatMap(userTeamList -> userTeamList.stream()).map

(UserDepartmentTeamDao::getStaffReference)
                .collect(Collectors.toSet());
