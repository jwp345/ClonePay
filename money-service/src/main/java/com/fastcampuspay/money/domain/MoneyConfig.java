package com.fastcampuspay.money.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.fastcampuspay.common") // common 패키지 있는 모두를 빈으로 등록
public class MoneyConfig {
}
