package fr.cfdt.gasel.groupeldap.mapper;

import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.model.Group;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class GroupMapperTest {

    private GroupMapper groupMapper = new GroupMapperImpl();

    @Test
    public void testGroupModelToDto() {
        //Given
        Group group = TestUtils.createGroup(Long.valueOf(1));

        //Then
        GroupDto groupDto = groupMapper.groupModelToDto(group);
        assertThat(groupDto, is(notNullValue()));
        assertEquals(groupDto.getId(), group.getIdGroup());
        assertEquals(groupDto.getDescription(), group.getDescription());
        assertEquals(groupDto.getName(), group.getName());
//        assertEquals(groupDto.getMembersNumber(), group.getMembersNumber());
        assertEquals(groupDto.getRequest().getIdRequest(),group.getRequest().getIdRequest());

        // test null
        Group groupNull = null;
        //Then
        GroupDto groupDtoNull = groupMapper.groupModelToDto(groupNull);
        assertThat(groupDtoNull, is(nullValue()));

        //test list group mapping
        List<Group> groups = TestUtils.createListGroup();
        //Then
        List<GroupDto> listGroupsDto = groupMapper.listGroupModelToDto(groups);
        assertThat(listGroupsDto, is(notNullValue()));
        assertEquals(3, listGroupsDto.size());

        // list groups is null
        List<Group> groupsNull = null;
        //Then
        List<GroupDto> listGroupsDtoNull = groupMapper.listGroupModelToDto(groupsNull);
        assertThat(listGroupsDtoNull, is(nullValue()));

    }

}
