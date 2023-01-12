package ua.koval.footballmanager.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.koval.footballmanager.controllers.PlayerController;
import ua.koval.footballmanager.controllers.TeamController;
import ua.koval.footballmanager.dto.PlayerListDto;
import ua.koval.footballmanager.dto.TeamDto;
import ua.koval.footballmanager.dto.TeamListDto;
import ua.koval.footballmanager.dto.TeamUpdateDto;
import ua.koval.footballmanager.entities.Player;
import ua.koval.footballmanager.entities.Team;
import ua.koval.footballmanager.repositories.TeamRepository;
import ua.koval.footballmanager.services.TeamService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional(readOnly = true)
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    public List<TeamListDto> findAll() {
        List<Team> teams = teamRepository.findAll();
        List<TeamListDto> teamListDtos = new ArrayList<>();

        for (Team team : teams) {
            TeamListDto teamListDto = modelMapper.map(team, TeamListDto.class);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(TeamController.class)
                    .getTeam(team.getId())).withRel("self");

            teamListDto.add(selfLink);
            teamListDtos.add(teamListDto);
        }

        return teamListDtos;
    }

    public TeamDto findById(int id) {
        Team team = teamRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        TeamDto teamDto = modelMapper.map(team, TeamDto.class);
        List<PlayerListDto> players = new ArrayList<>();

        for (Player player : team.getPlayers()) {
            PlayerListDto playerListDto = modelMapper.map(player, PlayerListDto.class);
            Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(PlayerController.class)
                    .getPlayer(player.getId())).withRel("self");

            playerListDto.add(selfLink);
            players.add(playerListDto);
        }

        teamDto.setPlayers(players);

        return teamDto;
    }

    @Transactional
    public TeamDto save(TeamUpdateDto teamUpdateDto) {
        Team team = new Team(teamUpdateDto.getName(), teamUpdateDto.getBalance(), teamUpdateDto.getCommission());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    @Transactional
    public TeamDto update(int id, TeamUpdateDto updatedTeam) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Team with id " + id + "  does not exist"));

        team.setName(updatedTeam.getName());
        team.setBalance(updatedTeam.getBalance());
        team.setCommission(updatedTeam.getCommission());

        return findById(id);
    }

    @Transactional
    public void delete(int id) {
        teamRepository.deleteById(id);
    }
}
