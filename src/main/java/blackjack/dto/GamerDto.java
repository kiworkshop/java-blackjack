package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamerDto {
    private final DealerDto dealerDto;
    private final List<PlayerDto> playersDto;

    public GamerDto(DealerDto dealerDto, List<PlayerDto> playersDto) {
        this.dealerDto = dealerDto;
        this.playersDto = Collections.unmodifiableList(new ArrayList<>(playersDto));
    }

    public DealerDto dealerDto() {
        return dealerDto;
    }

    public List<PlayerDto> playersDto() {
        return playersDto;
    }
}
