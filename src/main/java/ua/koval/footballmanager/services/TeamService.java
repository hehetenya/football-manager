package ua.koval.footballmanager.services;

import ua.koval.footballmanager.dto.TeamDto;
import ua.koval.footballmanager.dto.TeamListDto;
import ua.koval.footballmanager.dto.TeamUpdateDto;

import java.util.List;

public interface TeamService {
    List<TeamListDto> findAll();

    TeamDto findById(int id);

    TeamDto save(TeamUpdateDto teamUpdateDto);

    TeamDto update(int id, TeamUpdateDto updatedTeam);

    void delete(int id);
}
