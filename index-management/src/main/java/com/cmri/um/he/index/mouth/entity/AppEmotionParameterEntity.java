package com.cmri.um.he.index.mouth.entity;

import java.io.Serializable;
import java.util.Date;

public class AppEmotionParameterEntity implements Serializable {


    private int id;
    private String month;
    private int category;
    private int app;
    private int freqPositive;
    private int freqNegativity;
    private int freqNeutral;
    private int freqSum;
    private String  petPositive;
    private String  petNegativity;
    private String  petNeutral;
    private String  favorableRate;
    private double  favorableRateZ;
    private double  favorableRateT;
    private double  mauZ;
    private double  mauT;
    private double  emotionRateOriginal;
    private double  emotionRatez;
    private double  emotionScore;
    private Date atime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public int getFreqPositive() {
        return freqPositive;
    }

    public void setFreqPositive(int freqPositive) {
        this.freqPositive = freqPositive;
    }

    public int getFreqNegativity() {
        return freqNegativity;
    }

    public void setFreqNegativity(int freqNegativity) {
        this.freqNegativity = freqNegativity;
    }

    public int getFreqNeutral() {
        return freqNeutral;
    }

    public void setFreqNeutral(int freqNeutral) {
        this.freqNeutral = freqNeutral;
    }

    public int getFreqSum() {
        return freqSum;
    }

    public void setFreqSum(int freqSum) {
        this.freqSum = freqSum;
    }

    public String getPetPositive() {
        return petPositive;
    }

    public void setPetPositive(String petPositive) {
        this.petPositive = petPositive;
    }

    public String getPetNegativity() {
        return petNegativity;
    }

    public void setPetNegativity(String petNegativity) {
        this.petNegativity = petNegativity;
    }

    public String getPetNeutral() {
        return petNeutral;
    }

    public void setPetNeutral(String petNeutral) {
        this.petNeutral = petNeutral;
    }

    public String getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(String favorableRate) {
        this.favorableRate = favorableRate;
    }

    public double getFavorableRateZ() {
        return favorableRateZ;
    }

    public void setFavorableRateZ(double favorableRateZ) {
        this.favorableRateZ = favorableRateZ;
    }

    public double getFavorableRateT() {
        return favorableRateT;
    }

    public void setFavorableRateT(double favorableRateT) {
        this.favorableRateT = favorableRateT;
    }

    public double getMauZ() {
        return mauZ;
    }

    public void setMauZ(double mauZ) {
        this.mauZ = mauZ;
    }

    public double getMauT() {
        return mauT;
    }

    public void setMauT(double mauT) {
        this.mauT = mauT;
    }

    public double getEmotionRateOriginal() {
        return emotionRateOriginal;
    }

    public void setEmotionRateOriginal(double emotionRateOriginal) {
        this.emotionRateOriginal = emotionRateOriginal;
    }

    public double getEmotionRatez() {
        return emotionRatez;
    }

    public void setEmotionRatez(double emotionRatez) {
        this.emotionRatez = emotionRatez;
    }

    public double getEmotionScore() {
        return emotionScore;
    }

    public void setEmotionScore(double emotionScore) {
        this.emotionScore = emotionScore;
    }

    public Date getAtime() {
        return atime;
    }

    public void setAtime(Date atime) {
        this.atime = atime;
    }
}
