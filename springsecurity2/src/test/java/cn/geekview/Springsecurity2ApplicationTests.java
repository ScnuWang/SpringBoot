package cn.geekview;

import cn.geekview.entity.repository.SysResourceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springsecurity2ApplicationTests {

	@Autowired
	private SysResourceRepository resourceRepository;

	@Test
	public void contextLoads() {
		System.out.println(resourceRepository.findByRoleName("admin"));
	}

}
