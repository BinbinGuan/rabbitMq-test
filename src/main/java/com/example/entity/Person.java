package com.example.entity;

/**
 * @author: GuanBin
 * @date: Created in 下午7:20 2019/5/26
 */
public class Person {
    private final String name;
    private String location;
    private String job;
    private String habit;

    private Person(Builder builder) {
        name = builder.name;
        location = builder.location;
        job = builder.job;
        habit = builder.habit;
    }


    public static final class Builder {
        private String name;
        private String location;
        private String job;
        private String habit;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public Builder habit(String habit) {
            this.habit = habit;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
