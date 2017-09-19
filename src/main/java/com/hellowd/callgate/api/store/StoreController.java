package com.hellowd.callgate.api.store;

import com.hellowd.callgate.api.store.http.StoreCallgateIpsData;
import com.hellowd.callgate.api.store.http.StoreData;
import com.hellowd.callgate.core.annotation.bind.RestApiController;
import com.hellowd.callgate.core.entity.S3StoreEntity;
import com.hellowd.callgate.core.http.PageResultResponse;
import com.hellowd.callgate.core.http.ResultResponse;
import com.hellowd.callgate.core.util.pageUtil.PageUtil;
import com.hellowd.callgate.service.store.StoreService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestApiController(path = "/stores")
public class StoreController {

    ModelMapper modelMapper;
    StoreService storeService;

    @GetMapping
    public ResultResponse<List<StoreData>> getStoreList(Pageable pageable) {
        Page<S3StoreEntity> storeList = this.storeService.findStoreAll(pageable);
        return new PageResultResponse<>(storeList.getContent().stream().map(x -> modelMapper.map(x, StoreData.class)).collect(Collectors.toList()),
                new PageUtil(storeList.getTotalPages(), pageable.getPageNumber()));
    }

    @PostMapping
    public ResultResponse saveStore(@RequestBody StoreCallgateIpsData storeCallgateIpsData) {
        storeService.saveStoreCallGateIps(storeCallgateIpsData);
        return new ResultResponse(HttpStatus.OK);
    }
}
