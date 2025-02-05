package org.delivery.api.config

import lombok.RequiredArgsConstructor
import org.delivery.api.interceptor.AuthorizationInterceptor
import org.delivery.api.resolver.UserSessionResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@RequiredArgsConstructor
@Configuration
class WebConfig(
    private val interceptor: AuthorizationInterceptor,
    private val userSessionResolver: UserSessionResolver
) : WebMvcConfigurer {

    private val OPEN_API = listOf(
        "/open-api/**"
    )

    private val DEFAULT_EXCLUDE = listOf(
        "/",
        "favicon.ico",
        "/error",
        "/actuator/**"
    )

    private val SWAGGER = listOf(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    )

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor)
            .excludePathPatterns(OPEN_API)
            .excludePathPatterns(DEFAULT_EXCLUDE)
            .excludePathPatterns(SWAGGER)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userSessionResolver)
    }

}