package com.arke.sdk;

public class BettingStake {

    public String dateTime;
    public String coupon_code;
    public String slipId;
    public String userId;
    public String totalStake;
    public String totalOdds;
    public String potentialWinnings;
    public String bonus;
    public String bet_type;

    public int id;
    public String sportCategory;
    public String team1;
    public String team2;
    public String stake;

    public void NewStake(Integer id, String sportCategory, String team1, String team2, String stake){
//        super(id, sportCategory, team1, team2, stake);
        this.id = id;
        this.sportCategory = sportCategory;
        this.team1 = team1;
        this.team2 = team2;
        this.stake = stake;
    }

    public void stakeDetails(String slipId, String coupon_code, String userId, String bet_type, String dateTime, String totalStake, String totalOdds, String bonus, String potentialWinnings){
        this.slipId = slipId;
        this.coupon_code = coupon_code;
        this.bet_type = bet_type;
        this.userId = userId;
        this.dateTime = dateTime;
        this.totalStake = totalStake;
        this.totalOdds = totalOdds;
        this.bonus = bonus;
        this.potentialWinnings = potentialWinnings;
    }

}
