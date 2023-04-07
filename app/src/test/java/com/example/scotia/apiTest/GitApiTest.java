package com.example.scotia.apiTest;

import com.example.scotia.model.RepoEntity;
import com.example.scotia.model.User;
import com.example.scotia.model.data.api.ApiService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class GitApiTest {
    private MockWebServer server;
    private ApiService service;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        service = new Retrofit.Builder()
                .baseUrl(server.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    private void enqueueMockResponse(String jsonStr){
        MockResponse response = new MockResponse();
        response.setBody(jsonStr);
        server.enqueue(response);
    }


    @Test
    public void shouldReturnResponseWhenGetUserById() throws IOException, InterruptedException {
        String jsonStr = "{\"name\": \"The Octocat\",\"avatar_url\": \"https://avatars.githubusercontent.com/u/583231?v=4\"}";
        enqueueMockResponse(jsonStr);
        User user = service.getUserDataById("octocat").execute().body();
        RecordedRequest request = server.takeRequest();
        assertNotNull(user);
        assertEquals(request.getPath(), "/octocat");
    }

    @Test
    public void shouldReturnResponseWhenGetReposById() throws IOException, InterruptedException {
        String jsonStr = "[{\"description\": \"Testing\",\"name\": \"boysenberry-repo-1\",\"forks\": 12}," +
                "{\"description\": \"Testing2\",\"name\": \"boysenberry-repo-2\",\"forks\": 22}]";
                enqueueMockResponse(jsonStr);
        List<RepoEntity> repoEntities = service.getReposById("octocat").execute().body();
        RecordedRequest request = server.takeRequest();
        assertEquals(repoEntities.size(), 2);
        assertEquals(repoEntities.get(0).getForks(), 12);
        assertEquals(request.getPath(), "/octocat/repos");
    }


    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

}
