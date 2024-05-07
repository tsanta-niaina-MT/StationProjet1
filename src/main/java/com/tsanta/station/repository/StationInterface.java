package com.tsanta.station.repository;

import com.tsanta.station.model.Station;

import java.sql.SQLException;
import java.util.List;

public interface StationInterface {
    List<Station> getAllStations() throws SQLException;
    Station getStationById(String id) throws SQLException;
    Station deleteStation(String id) throws SQLException;
    Station updateStation(Station station) throws SQLException;
    Station updatePartialStation(Station station) throws SQLException;
    Station createStation(Station station) throws SQLException;
}
