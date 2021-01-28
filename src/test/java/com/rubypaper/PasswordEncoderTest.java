package com.rubypaper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class PasswordEncoderTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {

		Member member1 = new Member();
		member1.setId("member");
		member1.setPassword(encoder.encode("member123"));
		member1.setName("회원");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);

		Member member2 = new Member();
		member2.setId("manager");
		member2.setPassword(encoder.encode("manager123"));
		member2.setName("매니저");
		member2.setRole(Role.ROLE_MANAGER);
		member2.setEnabled(true);
		memberRepo.save(member2);

		Member member3 = new Member();
		member3.setId("admin");
		member3.setPassword(encoder.encode("admin123"));
		member3.setName("어드민");
		member3.setRole(Role.ROLE_ADMIN);
		member3.setEnabled(true);
		memberRepo.save(member3);
	}
}