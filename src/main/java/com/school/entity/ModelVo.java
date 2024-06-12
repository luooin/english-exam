package com.school.entity;

public class ModelVo {
    private Model model;
    private String type;
    private String difficulty;
    private Integer addFlag;

    public Integer getAddFlag() {
        return addFlag;
    }

    public void setAddFlag(Integer addFlag) {
        this.addFlag = addFlag;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return model.getModelId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (obj instanceof ModelVo) {
            ModelVo vo = (ModelVo) obj;
            // 比较每个属性的值 一致时才返回true
            if (vo.getModel().getModelId().equals(this.getModel().getModelId()))
                return true;
        }
        return false;
    }
}
