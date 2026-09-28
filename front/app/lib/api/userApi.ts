import { apiFetch, redirectToLogin } from "./client";
import { performLogout, startAuthentication } from "./tokenManager";

export type User = {
    id: number;
    email: string;
    name: string;
};

export type UserJoinRequest = {
    email: string;
    password: string;
    name: string;
};

export type UserLoginRequest = {
    email: string;
    password: string;
};

export type UserLoginResponse = {
    user: User;
    accessToken: string;
};

export async function signup(request: UserJoinRequest) {
    return apiFetch<User>(
        "/users/join",
        {
            method: "POST",
            body: request,
            redirectOnUnauthorized: false,
            skipTokenRefresh: true,
        },
    );
}

export async function login(request: UserLoginRequest) {
    const response = await apiFetch<UserLoginResponse>(
        "/users/login",
        {
            method: "POST",
            body: request,
            redirectOnUnauthorized: false,
            skipTokenRefresh: true,
        },
    );
    startAuthentication(response.accessToken);
    return response;
}

export async function logout() {
    try {
        await performLogout(
            () => apiFetch<{ accessToken: string }>("/users/refresh", {
                method: "POST",
                redirectOnUnauthorized: false,
                skipTokenRefresh: true,
            }),
            () => apiFetch<void>("/users/logout", {
                method: "DELETE",
                redirectOnUnauthorized: false,
                skipTokenRefresh: true,
            }),
        );
    } catch (error) {
        // 갱신/로그아웃 실패 시에도 자동 갱신을 종료합니다. 서버 성공으로 취급하지 않습니다.
        redirectToLogin();
        throw error;
    }
}

export async function getMe() {
    return apiFetch<User>(
        "/users/me",
    );
}