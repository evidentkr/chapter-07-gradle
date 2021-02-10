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
		member1.setId("member1");
		member1.setPassword(encoder.encode("member123"));
		//{bcrypt}$2a$10$ATXfBS7qSDxoW4Z5Y2ktD.Kn5wpW.ZJKwyz3FzuYoPUMkJJpMpnlm
		member1.setName("회원");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);

		Member member2 = new Member();
		member2.setId("manager1");
		member2.setPassword(encoder.encode("manager123"));
		//{bcrypt}$2a$10$kHzlk7eMozkl3ll2BIT9AOrkQ.QAW0Xxpp2XoonVlh1bxfsRLgZ6G
		member2.setName("매니저");
		member2.setRole(Role.ROLE_MANAGER);
		member2.setEnabled(true);
		memberRepo.save(member2);

		Member member3 = new Member();
		member3.setId("admin1");
		member3.setPassword(encoder.encode("admin123"));
		//{bcrypt}$2a$10$s/cTUKx5DKisgQPWzPbb1eSqQw5RU49eZGQwJ8J8uEDQl2XCyOjoO
		member3.setName("어드민");
		member3.setRole(Role.ROLE_ADMIN);
		member3.setEnabled(true);
		memberRepo.save(member3);
	}
}