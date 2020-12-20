package br.com.nextmove.condonext.utils;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbstractUtils {
    @Autowired
    protected ModelMapper mapper;
    protected final Logger log = LoggerFactory.getLogger(AbstractUtils.class);
}
