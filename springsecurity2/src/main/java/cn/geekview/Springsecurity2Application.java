package cn.geekview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@EnableAutoConfiguration //注意如果：MyFilterSecurityInterceptor.class
//@EnableAutoConfiguration(exclude = MyFilterSecurityInterceptor.class) //  没有才装配，有就以个人装配为准
public class Springsecurity2Application {

	@Autowired
	private RequestMappingHandlerConfig requestMappingHandlerConfig;

	public static void main(String[] args) {
		SpringApplication.run(Springsecurity2Application.class, args);
	}

	// 获取资源路径 : 只能获取Controller上面的路径，通过继承WebMvcConfigurerAdapter的类添加的路径映射，不能获取
	@PostConstruct
	public void detectresource(){
		final RequestMappingHandlerMapping requestMappingHandlerMapping = requestMappingHandlerConfig.requestMappingHandlerMapping ();
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		Set<RequestMappingInfo> mappings = map.keySet();
		Map<String, String> reversedMap = new HashMap<String, String>();
		for(RequestMappingInfo info : mappings) {
			HandlerMethod method = map.get(info);
			String methodstr = method.toString();
			methodstr = methodstr.split("\\(")[0];
			methodstr = methodstr.split(" ")[2];
			int i=methodstr.lastIndexOf(".");
			methodstr = methodstr.substring(0,i);
			String urlparm = info.getPatternsCondition().toString();
			String url = urlparm.substring(1, urlparm.length()-1);
			System.out.println(methodstr);
			System.out.println(url);
//			List<SysResource> list = sresourceService.findByResourceString(url);
//			if(list==null || list.size()<=0){
//				int num = (int)(Math.random()*100+1);
//				String rand = String.valueOf(num);
//				String resourceId = "res"+System.currentTimeMillis()+rand;
//				SysResource sysresource = new SysResource();
//				sysresource.setResourceString(url);
//				sysresource.setRemark("0");
//				sysresource.setResourceId(resourceId);
//				sysresource.setMethodPath(methodstr);
//				sresourceService.save(sysresource);
//				System.out.println ("===>"+url);
//			}

		}
	}
}