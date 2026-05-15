package com.stock.demo.infrastructure.configuration;


import com.stock.demo.domain.api.IArticleServicePort;
import com.stock.demo.domain.api.IBrandServicePort;
import com.stock.demo.domain.api.ICategoryServicePort;
import com.stock.demo.domain.spi.IArticlePersistencePort;
import com.stock.demo.domain.spi.IBrandPersistencePort;
import com.stock.demo.domain.spi.ICategoryPersistencePort;
import com.stock.demo.domain.usecase.ArticleUseCase;
import com.stock.demo.domain.usecase.BrandUseCase;
import com.stock.demo.domain.usecase.CategoryUseCase;
import com.stock.demo.infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import com.stock.demo.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.stock.demo.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.stock.demo.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import com.stock.demo.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.stock.demo.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.stock.demo.infrastructure.output.jpa.repository.IArticleRepository;
import com.stock.demo.infrastructure.output.jpa.repository.IBrandRepository;
import com.stock.demo.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//we completely isolated our domain from technologies like spring,
//so we need to create a configuration class. This class helps us
// because it tells spring that it needs to work with things in the domain as if they were BEANS
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(
                categoryRepository, categoryEntityMapper);
    }
    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
    @Bean
    public IBrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(
                brandRepository, brandEntityMapper);
    }
    @Bean
    public IBrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort());
    }
    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(
                articleRepository,articleEntityMapper
//        ,categoryRepository,categoryEntityMapper
        );
    }
    @Bean
    public IArticleServicePort articleServicePort() {
        return new ArticleUseCase(
                articlePersistencePort()
        ,categoryPersistencePort()
        ,brandPersistencePort());
    }
}
