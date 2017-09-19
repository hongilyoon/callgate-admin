package com.hellowd.callgate.api.callgate;

import com.hellowd.callgate.core.annotation.bind.RestApiController;
import com.hellowd.callgate.core.http.ResultResponse;
import com.hellowd.callgate.service.callgate.CallGateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestApiController(path = "/callgate")
public class CallGateController {

    CallGateService callGateService;

    @PostMapping("/{storeSeq}")
    public ResultResponse saveCallGate(@PathVariable("storeSeq") Long storeSeq) {
        callGateService.saveCallGate(storeSeq);
        return new ResultResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{storeSeq}")
    public ResultResponse deleteCallGate(@PathVariable("storeSeq") Long storeSeq) {
        callGateService.deleteCallGate(storeSeq);
        return new ResultResponse(HttpStatus.OK);
    }


}
