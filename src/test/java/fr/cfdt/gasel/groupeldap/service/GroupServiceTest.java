package fr.cfdt.gasel.groupeldap.service;

import fr.cfdt.gasel.groupeldap.LdapGroupApplicationTests;
import fr.cfdt.gasel.groupeldap.connector.groupdb.GroupRepository;
import fr.cfdt.gasel.groupeldap.dto.GroupDto;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;
import fr.cfdt.gasel.groupeldap.mapper.GroupMapper;
import fr.cfdt.gasel.groupeldap.util.TestUtils;
import fr.cfdt.gasel.ldap.GaselLDAPService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Author SZaoui
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {LdapGroupApplicationTests.class})
public class GroupServiceTest {

    @Mock
    GroupRepository groupRepository;

    @Mock
    GroupMapper groupMapper;

    @Mock
    GaselLDAPService gaselLDAPService;

    @Mock
    PersonService personService;

    @InjectMocks
    GroupService groupService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllGroups(){
        // When
        when(groupRepository.findAll()).thenReturn(TestUtils.createListGroup());
        when(groupMapper.listGroupModelToDto(any(List.class))).thenReturn(TestUtils.createListGroupDto());

        //Then
        List<GroupDto> groups = groupService.getAllGroups();

        assertThat(groups, is(notNullValue()));
        assertEquals(Long.valueOf(1), groups.get(0).getId());
        assertEquals("Groupe1", groups.get(0).getName());
        assertEquals(Long.valueOf(2), groups.get(1).getId());
        assertEquals("Groupe2", groups.get(1).getName());
    }

    @Test
    public void testDeleteGroup(){
        //when
//        when(groupRepository.findById(any())).thenReturn(Optional.ofNullable(TestUtils.createGroup(Long.valueOf(1))));
//        Mockito.doNothing().when(requestRepository).deleteById(any());
////        Mockito.doNothing().when(groupService).deleteGroup(any());
//        //then
//        Group group = TestUtils.createGroup(Long.valueOf(1));
//        groupService.deleteGroup(group.getIdGroup());
//        verify(groupService, times(1)).deleteGroup(group.getIdGroup());
    }

    @Test
    public void testCreateGroup() throws TechnicalException {
        when(groupRepository.save(any())).thenReturn(TestUtils.createGroup(Long.valueOf(1)));
        when(groupMapper.groupModelToDto(any())).thenReturn(TestUtils.createGroupDto(Long.valueOf(1)));
        when(gaselLDAPService.createLpdapGroup(any())).thenReturn(true);
        when(personService.getMembers(any(),any(),any())).thenReturn(TestUtils.createPersonPageDto());
        //then
        GroupDto group = TestUtils.createGroupDto(Long.valueOf(2));
        GroupDto result = groupService.createGroup(group);

        assertThat(result, is(notNullValue()));
        assertEquals(Long.valueOf(1), result.getId());
        assertEquals("Groupe1", result.getName());
    }
}
