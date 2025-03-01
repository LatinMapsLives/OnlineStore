package by.demidov_a_r.onlinestore.service;


import by.demidov_a_r.onlinestore.dto.ProductCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.ProductFilter;
import by.demidov_a_r.onlinestore.dto.ProductReadDTO;
import by.demidov_a_r.onlinestore.mapper.ProductCreateEditMapper;
import by.demidov_a_r.onlinestore.mapper.ProductReadMapper;
import by.demidov_a_r.onlinestore.model.entity.Product;
import by.demidov_a_r.onlinestore.model.entity.User;
import by.demidov_a_r.onlinestore.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCreateEditMapper productCreateEditMapper;
    private final ProductReadMapper productReadMapper;

    public Page<ProductReadDTO> findAllByFilter(ProductFilter filter,
                                         Pageable pageable) {
        return productRepository.findAllFilter(filter, pageable)
                .map(productReadMapper::mapTo);
    }

    @Transactional
    public Optional<ProductReadDTO> create(ProductCreateEditDTO productCreateEditDTO){
        return Optional.of(productRepository.saveAndFlush(productCreateEditMapper.mapTo(productCreateEditDTO)))
                .map(productReadMapper::mapTo);
    }

    @Transactional
    public Optional<ProductReadDTO> update(Long id, ProductCreateEditDTO productCreateEditDTO) {
        return productRepository.findById(id)
                .map(entity ->
                        productCreateEditMapper.copy(productCreateEditDTO, entity))
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::mapTo);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public Optional<ProductReadDTO> findById(Long id){
        return productRepository.findById(id).map(productReadMapper::mapTo);
    }

    public Product dtoToEntity(ProductReadDTO productReadDTO){
        return productReadMapper.reverseMap(productReadDTO);
    }
}
