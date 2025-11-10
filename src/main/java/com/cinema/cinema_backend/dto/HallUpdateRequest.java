package com.cinema.cinema_backend.dto;

import java.util.Optional;

public class HallUpdateRequest {

    private Optional<String> name = Optional.empty();
    private Optional<Integer> capacity = Optional.empty();

    public HallUpdateRequest() {}

    public Optional<String> getName() { return name; }
    public void setName(Optional<String> name) { this.name = name; }

    public Optional<Integer> getCapacity() { return capacity; }
    public void setCapacity(Optional<Integer> capacity) { this.capacity = capacity; }
}
