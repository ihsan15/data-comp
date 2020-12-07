package com.ihsan.playermarket.service;

import com.ihsan.playermarket.controller.request.InsertTeamRequest;
import com.ihsan.playermarket.entity.Team;

public interface TeamService {

    Long addTeam(InsertTeamRequest request);

    Team get(long teamId);

    Long updateTeam(long teamId, InsertTeamRequest request);

    Long deleteTeam(long teamId);
}
