package by.demidov_a_r.onlinestore.dto;

import lombok.Value;
import org.springframework.data.domain.Page;

import java.util.List;

@Value
public class PageResponse<T>{

    List<T> content;
    Metadata metadata;

    public static <T> PageResponse<T> of(Page<T> page){
        Metadata metadata = new Metadata(page.getTotalPages(), page.getSize(), page.getTotalElements());
        List<T> content = page.getContent();
        return new PageResponse<>(content, metadata);
    }

    @Value
    public static class Metadata{
        int page;
        int size;
        long totalElements;
    }

}
