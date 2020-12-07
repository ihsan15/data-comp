package com.ihsan.playermarket.controller.request;

import com.ihsan.playermarket.entity.Team;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class InsertTeamRequest {

    @ApiModelProperty
    private Team team;

    public InsertTeamRequest(){

    }

    public InsertTeamRequest(Team team){
        this.team =team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
