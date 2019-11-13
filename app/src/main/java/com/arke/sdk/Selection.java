package com.arke.sdk;

public class Selection {
    String id;
    int bet_id;
    int event_id;
    int provider_id;
    String element_id;
    String sport;
    String category;
    String tournament;
    String event;
    String start_date;
    int market_id;
    String market_name;
    String specifiers;
    int odd_id;
    String odd_name;
    String type;
    float odds;
    String score;
    int status;
    String settled_at;
    String created_at;
    String updated_at;

    public Selection(String id, int bet_id, int event_id, int provider_id, String element_id, String sport, String category, String tournament, String event, String start_date, int market_id, String market_name, String specifiers, int odd_id, String odd_name, String type, float odds, String score, int status, String settled_at, String created_at, String updated_at) {
        this.id = id;
        this.bet_id = bet_id;
        this.event_id = event_id;
        this.provider_id = provider_id;
        this.element_id = element_id;
        this.sport = sport;
        this.category = category;
        this.tournament = tournament;
        this.event = event;
        this.start_date = start_date;
        this.market_id = market_id;
        this.market_name = market_name;
        this.specifiers = specifiers;
        this.odd_id = odd_id;
        this.odd_name = odd_name;
        this.type = type;
        this.odds = odds;
        this.score = score;
        this.status = status;
        this.settled_at = settled_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getElement_id() {
        return element_id;
    }

    public void setElement_id(String element_id) {
        this.element_id = element_id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getMarket_id() {
        return market_id;
    }

    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getSpecifiers() {
        return specifiers;
    }

    public void setSpecifiers(String specifiers) {
        this.specifiers = specifiers;
    }

    public int getOdd_id() {
        return odd_id;
    }

    public void setOdd_id(int odd_id) {
        this.odd_id = odd_id;
    }

    public String getOdd_name() {
        return odd_name;
    }

    public void setOdd_name(String odd_name) {
        this.odd_name = odd_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getOdds() {
        return odds;
    }

    public void setOdds(float odds) {
        this.odds = odds;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSettled_at() {
        return settled_at;
    }

    public void setSettled_at(String settled_at) {
        this.settled_at = settled_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
