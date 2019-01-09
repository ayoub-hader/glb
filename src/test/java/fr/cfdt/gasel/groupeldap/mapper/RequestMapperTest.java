package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.RequestDto;
import fr.cfdt.gasel.groupeldap.model.Request;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * AccountClient Tester.
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class RequestMapperTest {

    private RequestMapper requestMapper = new RequestMapperImpl();

    @Test
    public void testRequestModelToDto() {
        //Given
        Request request = TestUtils.createRequest(1);

        //Then
        RequestDto requestDto = requestMapper.mapRequestModeltodto(request);
        assertThat(requestDto, is(notNullValue()));
        assertEquals(requestDto.getIdRequest(), request.getIdRequest());
        assertEquals(requestDto.getCreationDate(), request.getCreationDate());
        assertEquals(requestDto.getExecutor(), request.getExecutor());
        assertEquals(requestDto.getLastExecutionDate(), request.getLastExecutionDate());
        assertEquals(requestDto.getRequest(),request.getRequest());

        // test null
        Request requestNull = null;
        //Then
        RequestDto requestDtoNull = requestMapper.mapRequestModeltodto(requestNull);
        assertThat(requestDtoNull, is(nullValue()));

        //test list group mapping
        List<Request> requests = TestUtils.createListRequest();
        //Then
        List<RequestDto> listRequestsDto = requestMapper.mapListRequestModeltodto(requests);
        assertThat(listRequestsDto, is(notNullValue()));
        assertEquals(3, listRequestsDto.size());

        // list groups is null
        List<Request> requestsNull = null;
        //Then
        List<RequestDto> listRequestsDtoNull = requestMapper.mapListRequestModeltodto(requestsNull);
        assertThat(listRequestsDtoNull, is(nullValue()));

    }

}
