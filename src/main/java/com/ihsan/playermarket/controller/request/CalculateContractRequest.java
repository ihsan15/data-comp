package com.ihsan.playermarket.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CalculateContractRequest {

    @ApiModelProperty
    private long teamId;

    @ApiModelProperty
    private long playerId;

    public CalculateContractRequest(){

    }

    public CalculateContractRequest(long teamId, long playerId) {
        this.teamId = teamId;
        this.playerId = playerId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
