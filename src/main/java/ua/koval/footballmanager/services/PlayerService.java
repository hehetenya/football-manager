package ua.koval.footballmanager.services;

import ua.koval.footballmanager.dto.PlayerDto;
import ua.koval.footballmanager.dto.PlayerUpdateDto;
import ua.koval.footballmanager.dto.PlayerListDto;

import java.util.List;

public interface PlayerService {
    List<PlayerListDto> findAll();

    PlayerDto findById(int id);

    PlayerDto save(PlayerUpdateDto player);

    PlayerDto update(int id, PlayerUpdateDto updatedPlayer);

    void delete(int id);

    PlayerDto transferPlayer(int id, int teamId);
}
