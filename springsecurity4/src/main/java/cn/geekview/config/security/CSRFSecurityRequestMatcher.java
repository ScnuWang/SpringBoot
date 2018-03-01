package cn.geekview.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *  自定义CSRF匹配，排除不需要CSRF防御的资源，比如给第三方开放的接口
 *  默认使用的是DefaultRequiresCsrfMatcher
 */
@Slf4j
public class CSRFSecurityRequestMatcher implements RequestMatcher {

    private final HashSet<String> allowedMethods = new HashSet<String>(
            Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));
    //需要排除URL
    private Set<String> excludedUrls ;

    @Override
    public boolean matches(HttpServletRequest request) {
        if (excludedUrls!=null && excludedUrls.size()>0){
            String seveletPath = request.getServletPath();
            for (String excludedUrl : excludedUrls) {
                if (seveletPath.contains(excludedUrl)){
                    return false;
                }
            }
        }
        return !this.allowedMethods.contains(request.getMethod());
    }

    public Set<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(Set<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
}
