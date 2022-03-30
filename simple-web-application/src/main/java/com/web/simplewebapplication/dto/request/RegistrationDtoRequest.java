package com.web.simplewebapplication.dto.request;

public class RegistrationDtoRequest {
    private String email;
    private String password;
    private String rePassword;
    private String name;
    private Integer age;

    public RegistrationDtoRequest(String email, String password, String rePassword, String name, Integer age) {
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.name = name;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "RegistrationDtoRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
