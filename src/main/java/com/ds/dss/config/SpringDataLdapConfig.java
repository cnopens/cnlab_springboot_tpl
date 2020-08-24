package com.ds.dss.config;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import java.util.List;


@Configuration
@EnableLdapRepositories
public class SpringDataLdapConfig {



    @Bean
    ContextSource contextSource() {
//		Gson gson = new Gson();
//		LdapDto ldapDto =gson.fromJson(jsonStr, LdapDto.class);
//        LdapContextSource ldapContextSource = new LdapContextSource();
//        ldapContextSource.setBase(ldapDto.getBase());
//        ldapContextSource.setUrl(ldapDto.getUrl());
//        ldapContextSource.setUserDn(ldapDto.getUserDn());
//        ldapContextSource.setPassword(ldapDto.getPassword());

        return null;
    }

    @Bean
    LdapTemplate ldapTemplate(ContextSource contextSource) {
        return new LdapTemplate(contextSource);
    }

}