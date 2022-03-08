package com.revature.services;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import junit.framework.TestCase;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ReimbursementServiceTest extends TestCase {


    public void testCreateReimbursement() {
        //not mocked
        ReimbursementDao rd = new ReimbursementDaoImpl();
        ReimbursementService rs = new ReimbursementService(rd);

        Timestamp submitted = Timestamp.valueOf(LocalDateTime.of(2022, 2, 4, 0, 0));
        Timestamp resolved = Timestamp.valueOf(LocalDateTime.of(2022, 2,5, 0, 0));

        Reimbursement test = rs.createReimbursement(1, 10.00f,
                submitted,
                resolved,
                "",3, 4, 5, 0);

        assertNotNull(test);
    }

    public void testCreate(){
        //we're mocking the Dao class so it doesn't connect to the DB
        ReimbursementDao rd = mock(ReimbursementDaoImpl.class);
        //service class needs to take a dao class object inorder to inject the mock
        ReimbursementService rs = new ReimbursementService(rd);

        //when method is being run, the returns an already prepared response
        when(rd.create(any())).thenReturn(true);

        //rs will run rd methods making the when mock test run
        boolean status = rs.create(100.00f, "test", 1,1);
        //test to see if the mock is successfully run
        //objective is to call the service method without calling the db
        assertTrue(status);
    }

    public void testUpdateStatus() {
        ReimbursementDao rd = mock(ReimbursementDaoImpl.class);
        ReimbursementService rs = new ReimbursementService(rd);

        Timestamp submitted = Timestamp.valueOf(LocalDateTime.of(2022, 2, 4, 0, 0));
        Timestamp resolved = Timestamp.valueOf(LocalDateTime.of(2022, 2,5, 0, 0));

        Reimbursement r = new Reimbursement(1, 10.00f,
                submitted,
                resolved,
                "",3, 4, 5, 0);

        when(rd.updateStatus(any())).thenReturn(true);
        boolean status = rs.updateStatus(r);
        assertTrue(status);
    }


    public void testGetAll() {
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

        when(rd.getAll()).thenReturn(list);
        List<Reimbursement> test = rs.getAll();
        assertNotNull(test);
    }

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

        when(rd.getAllPending()).thenReturn(list);
        List<Reimbursement> test = rs.getAllPending();

        assertNotNull(test);
    }

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

        when(rd.getAllResolved()).thenReturn(list);
        List<Reimbursement> test = rs.getAllResolved();

        assertNotNull(test);
    }

    public void testGetByAuthor() {
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

        when(rd.getByAuthor(anyInt())).thenReturn(list);
        List<Reimbursement> test = rs.getByAuthor(1);
        assertNotNull(test);
    }

    public void testGetByAuthorAndPending() {
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

        when(rd.getByAuthorAndPending(anyInt())).thenReturn(list);
        List<Reimbursement> test = rs.getByAuthorAndPending(1);
        assertNotNull(test);
    }

    public void testGetByAuthorAndResolved() {
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

        when(rd.getByAuthorAndResolved(anyInt())).thenReturn(list);
        List<Reimbursement> test = rs.getByAuthorAndResolved(1);
        assertNotNull(test);
    }
}