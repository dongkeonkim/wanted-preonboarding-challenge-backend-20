package wanted_market.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted_market.member.dto.MemberDto;
import wanted_market.member.service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        Optional<MemberDto> memberDto = memberService.findByMemberId(id);

        if (memberDto.isEmpty()) {
            return new ResponseEntity<>("멤버가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("멤버가 존재합니다.", HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberDto memberDto) {

        try {
            memberService.join(memberDto);
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
    }
}
