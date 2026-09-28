"use client";

import { useEffect, useRef, useState } from "react";

import { logout } from "../../../lib/api/userApi";

type UserProfileMenuProps = {
    userName: string;
    userRole?: string;
};

export default function UserProfileMenu({
    userName,
    userRole,
}: UserProfileMenuProps) {
    const [isOpen, setIsOpen] = useState(false);
    const [isLoggingOut, setIsLoggingOut] = useState(false);
    const menuRef = useRef<HTMLDivElement>(null);
    const displayName = userName || "사용자";

    useEffect(() => {
        if (!isOpen) {
            return;
        }

        const closeOnOutsideClick = (event: PointerEvent) => {
            if (!menuRef.current?.contains(event.target as Node)) {
                setIsOpen(false);
            }
        };

        const closeOnEscape = (event: KeyboardEvent) => {
            if (event.key === "Escape") {
                setIsOpen(false);
            }
        };

        document.addEventListener("pointerdown", closeOnOutsideClick);
        document.addEventListener("keydown", closeOnEscape);

        return () => {
            document.removeEventListener("pointerdown", closeOnOutsideClick);
            document.removeEventListener("keydown", closeOnEscape);
        };
    }, [isOpen]);

    const handleLogout = async () => {
        setIsOpen(false);
        setIsLoggingOut(true);

        try {
            await logout();
            window.location.replace("/login");
        } catch (error) {
            console.error("로그아웃에 실패했습니다.", error);
        } finally {
            setIsLoggingOut(false);
        }
    };

    return (
        <div ref={menuRef} className="relative">
            <button
                type="button"
                aria-expanded={isOpen}
                aria-haspopup="menu"
                onClick={() => setIsOpen((previous) => !previous)}
                className="flex w-full items-center gap-3 rounded-xl p-2 text-left transition-colors hover:bg-zinc-50 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
            >
                <span className="flex h-10 w-10 shrink-0 items-center justify-center rounded-full bg-zinc-800 text-base font-bold text-white">
                    {displayName.charAt(0)}
                </span>

                <span className="min-w-0 flex-1">
                    <span className="block truncate text-sm font-semibold text-zinc-800">
                        {displayName}
                    </span>

                    {userRole && (
                        <span className="mt-0.5 block text-xs text-zinc-500">
                            {userRole}
                        </span>
                    )}
                </span>
            </button>

            {isOpen && (
                <div
                    role="menu"
                    aria-label="사용자 메뉴"
                    className="absolute right-0 top-[calc(100%+0.5rem)] z-40 min-w-36 rounded-xl border border-zinc-200 bg-white p-1 shadow-lg"
                >
                    <button
                        type="button"
                        role="menuitem"
                        disabled={isLoggingOut}
                        onClick={() => void handleLogout()}
                        className="w-full rounded-lg px-3 py-2 text-left text-sm font-medium text-zinc-700 transition-colors hover:bg-zinc-100 disabled:cursor-not-allowed disabled:opacity-60"
                    >
                        {isLoggingOut ? "로그아웃 중..." : "로그아웃"}
                    </button>
                </div>
            )}
        </div>
    );
}
