package com.revature.daos;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@Deprecated
public class ReimbursementDaoImplTest extends TestCase {

    @Mock
    static ReimbursementDao rd;
    
    @Test
    public void testGetAll() {
        //create a list to get the return object from the mock
        List<Reimbursement> list = new ArrayList<>();
        //Mock the class we're testing that requires db connection
        ReimbursementDao rd = mock(ReimbursementDaoImpl.class);
        ReimbursementService rs = new ReimbursementService(rd);

        Timestamp submitted = Timestamp.valueOf(LocalDateTime.of(2022, 2, 4, 0, 0));
        Timestamp resolved = Timestamp.valueOf(LocalDateTime.of(2022, 2,5, 0, 0));

        Reimbursement r = new Reimbursement(1, 10.00f,
                submitted,
                resolved,
                "",3, 4, 5, 0);
        list.add(r);

        List<Reimbursement> test = rs.getAll();
        //when it calls the function we're testing it returns the expected return type
        when(rd.getAll()).thenReturn(list);
        //makes sure that the test object has something;
        assertNotNull(test);
    }

    @Test
    public void testGetAllPending() {
        List<Reimbursement> list = new ArrayList<>();
        ReimbursementDao rd = mock(ReimbursementDaoImpl.class);
        ReimbursementService rs = new ReimbursementService(rd);

        Timestamp submitted = Timestamp.valueOf(LocalDateTime.of(2022, 2, 4, 0, 0));
        Timestamp resolved = Timestamp.valueOf(LocalDateTime.of(2022, 2,5, 0, 0));

        Reimbursement r = new Reimbursement(1, 10.00f,
                submitted,
                resolved,
                "",3, 4, 5, 0);
        list.add(r);

        List<Reimbursement> test = rs.getAllPending();

        when(rd.getAllPending()).thenReturn(list);

        assertNotNull(test);
    }

    @Test
    public void testGetAllResolved() {
        List<Reimbursement> list = new ArrayList<>();
        ReimbursementDao rd = mock(ReimbursementDaoImpl.class);

        ReimbursementService rs = new ReimbursementService(rd);

        Timestamp submitted = Timestamp.valueOf(LocalDateTime.of(2022, 2, 4, 0, 0));
        Timestamp resolved = Timestamp.valueOf(LocalDateTime.of(2022, 2,5, 0, 0));

        Reimbursement r = new Reimbursement(1, 10.00f,
                submitted,
                resolved,
                "",3, 4, 5, 0);
        list.add(r);

        List<Reimbursement> test = rs.getAllResolved();

        when(rd.getAllResolved()).thenReturn(list);
        System.out.println(test);
        assertNotNull(test);
    }

/*
    @Test
    public void testCreate() {
        ReimbursementService rs = new ReimbursementService();

        ReimbursementDao rd = Mockito.mock(ReimbursementDaoImpl.class);

        Reimbursement r = rs.createReimbursement(1, 10.00f,
                LocalDateTime.of(2022, 2, 4, 0, 0),
                LocalDateTime.of(2022, 2,5, 0, 0),
                "",1, 2, 1, 1);

        boolean result = rd.create(r);

        doNothing().when(rd.create(any()));
        //when(rd.create(any().)).thenReturn(true);
        verify(rd).create(any());

        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        ReimbursementService rs = new ReimbursementService();

        ReimbursementDao rd = mock(ReimbursementDaoImpl.class);
        ReimbursementDao testDao = new ReimbursementDaoImpl();

        boolean result = testDao.update(new Reimbursement());

        when(rd.update(any())).thenReturn(true);

        assertTrue(result);
    }
    */
}