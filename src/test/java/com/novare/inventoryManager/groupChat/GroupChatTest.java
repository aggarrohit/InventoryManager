package com.novare.inventoryManager.groupChat;

import com.novare.inventoryManager.employees.Employee;
import com.novare.inventoryManager.employees.EmployeeRole;
import com.novare.inventoryManager.utils.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class GroupChatTest {

    @Mock
    GroupChatModel mockModel;

    @Mock
    GroupChatView mockView;

    List<Message> chats;

    @BeforeEach
    public void populateDataAndInit(){
        MockitoAnnotations.openMocks(this);
        chats = GroupChatTestData.getChats();
    }

    @Test
    public void testForSendingChat() throws FileNotFoundException, NoSuchAlgorithmException {


        Employee loggedInEmployee = new Employee("Rohit","1111-roh",BigDecimal.ZERO, EmployeeRole.CASHIER);

        String messageToSend = "this is a text message";
        when(mockModel.getTextInput()).thenReturn(messageToSend,"0");

        Message message = new Message(  UUID.randomUUID(),
                loggedInEmployee.getFullName()+"("+loggedInEmployee.getRole()+")",
                loggedInEmployee.getId(),
                Utilities.convertDateTimeToLong(new Date()),
                messageToSend
        );

        doAnswer(invocationOnMock -> {

            chats.add(message);

            return null;
        }).when(mockModel).addMessageToGroupChat(messageToSend,loggedInEmployee);


        new GroupChatController(mockModel,mockView,loggedInEmployee);

        Assertions.assertEquals(message,chats.get(chats.size()-1));

    }

}