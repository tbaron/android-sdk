package com.infospace.sdk.util;

public interface ILog {
	/**
	 * Send a {@link #VERBOSE} log message.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	int v(String tag, String msg);

	/**
	 * Send a {@link #VERBOSE} log message and log the exception.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param tr  An exception to log
	 */
	int v(String tag, String msg, Throwable tr);

	/**
	 * Send a {@link #DEBUG} log message.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	int d(String tag, String msg);

	/**
	 * Send a {@link #DEBUG} log message and log the exception.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param tr  An exception to log
	 */
	int d(String tag, String msg, Throwable tr);

	/**
	 * Send an {@link #INFO} log message.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	int i(String tag, String msg);

	/**
	 * Send a {@link #INFO} log message and log the exception.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param tr  An exception to log
	 */
	int i(String tag, String msg, Throwable tr);

	/**
	 * Send a {@link #WARN} log message.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	int w(String tag, String msg);

	/**
	 * Send a {@link #WARN} log message and log the exception.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param tr  An exception to log
	 */
	int w(String tag, String msg, Throwable tr);

	/*
	 * Send a {@link #WARN} log message and log the exception.
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *        the class or activity where the log call occurs.
	 * @param tr An exception to log
	 */
	int w(String tag, Throwable tr);

	/**
	 * Send an {@link #ERROR} log message.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	int e(String tag, String msg);

	/**
	 * Send a {@link #ERROR} log message and log the exception.
	 *
	 * @param tag Used to identify the source of a log message.  It usually identifies
	 *            the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param tr  An exception to log
	 */
	int e(String tag, String msg, Throwable tr);

	/**
	 * What a Terrible Failure: Report a condition that should never happen.
	 * The error will always be logged at level ASSERT with the call stack.
	 * Depending on system configuration, a report may be added to the
	 * {@link android.os.DropBoxManager} and/or the process may be terminated
	 * immediately with an error dialog.
	 *
	 * @param tag Used to identify the source of a log message.
	 * @param msg The message you would like logged.
	 */
	int wtf(String tag, String msg);

	/**
	 * What a Terrible Failure: Report an exception that should never happen.
	 * Similar to {@link #wtf(String, String)}, with an exception to log.
	 *
	 * @param tag Used to identify the source of a log message.
	 * @param tr  An exception to log.
	 */
	int wtf(String tag, Throwable tr);

	/**
	 * What a Terrible Failure: Report an exception that should never happen.
	 * Similar to {@link #wtf(String, Throwable)}, with a message as well.
	 *
	 * @param tag Used to identify the source of a log message.
	 * @param msg The message you would like logged.
	 * @param tr  An exception to log.  May be null.
	 */
	int wtf(String tag, String msg, Throwable tr);

	/**
	 * Handy function to get a loggable stack trace from a Throwable
	 *
	 * @param tr An exception to log
	 */
	String getStackTraceString(Throwable tr);

	/**
	 * Low-level logging call.
	 *
	 * @param priority The priority/type of this log message
	 * @param tag      Used to identify the source of a log message.  It usually identifies
	 *                 the class or activity where the log call occurs.
	 * @param msg      The message you would like logged.
	 * @return The number of bytes written.
	 */
	int println(int priority, String tag, String msg);
}
