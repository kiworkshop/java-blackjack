package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantDto {
    private final DealerDto dealerDto;
    private final List<PlayerDto> playersDto;

    public ParticipantDto(DealerDto dealerDto, List<PlayerDto> playersDto) {
        this.dealerDto = dealerDto;
        this.playersDto = Collections.unmodifiableList(new ArrayList<>(playersDto));
    }

    public DealerDto getDealerDto() {
        return dealerDto;
    }

    public List<PlayerDto> getPlayers() {
        return playersDto;
    }
}
