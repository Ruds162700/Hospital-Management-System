/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

/**
 *
 * @author rudra
 */
public class Specialist {
    private int id;
    private String SpecialistName;

      public Specialist() {
         super(); 
    }
    
    
    public Specialist(int id, String SpecialistName) {
        super();
        this.id = id;
        this.SpecialistName = SpecialistName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialistName() {
        return SpecialistName;
    }

    public void setSpecialistName(String SpecialistName) {
        this.SpecialistName = SpecialistName;
    }
    
    
}
