package nl.roymprog.cardsscore.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {

  String id;
  LocalDateTime joinedOn;
  String username;
  List<ChineesPoepenResponse> games;

}