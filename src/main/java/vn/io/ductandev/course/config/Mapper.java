package vn.io.ductandev.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper<T> {
	
	@Autowired
	private Config config;
	
	public <D> D convert(T sourceObject, Class<D> destinationType) {
        return config.modelMapper().map(sourceObject, destinationType);
    }
	
}
