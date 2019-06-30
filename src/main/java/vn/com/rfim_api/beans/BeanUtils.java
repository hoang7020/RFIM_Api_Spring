package vn.com.rfim_api.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.com.rfim_api.utils.PropertiesUtil;

import java.util.Properties;

@Configuration
public class BeanUtils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
}
