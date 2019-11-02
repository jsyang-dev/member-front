package com.example.memberfront.service;

import com.example.memberfront.common.RestTemplateHelperImpl;
import com.example.memberfront.dto.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MemberServiceImpl implements MemberService {
    @Value("${app.api.member.scheme}")
    private String scheme;

    @Value("${app.api.member.host}")
    private String host;

    @Value("${app.api.member.port}")
    private String port;

    private final RestTemplateHelperImpl restTemplateHelper;

    public MemberServiceImpl(RestTemplateHelperImpl restTemplateHelper) {
        this.restTemplateHelper = restTemplateHelper;
    }

    @Override
    public Member readMember(String username) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(this.scheme)
                .host(this.host)
                .port(this.port)
                .path("/api/v1/account")
                .path("/" + username)
                .build(true);

        return this.restTemplateHelper.getForEntity(Member.class, uriComponents.toUriString());
    }

    @Override
    public String createMember(Member member) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(this.scheme)
                .host(this.host)
                .port(this.port)
                .path("/api/v1/account")
                .build(true);

        return this.restTemplateHelper.postForEntity(String.class, uriComponents.toUriString(), member);
    }

    @Override
    public String modifyMember(Member member) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(this.scheme)
                .host(this.host)
                .port(this.port)
                .path("/api/v1/account")
                .build(true);

        return this.restTemplateHelper.putForEntity(String.class, uriComponents.toUriString(), member);
    }
}
